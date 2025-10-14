import http from 'k6/http';
import { check, sleep } from 'k6';

// The URL for your live service.
const BASE_URL = 'https://todo-service-400624648397.asia-south1.run.app';

export const options = {
    // Define the stages for our load test.
    // We will ramp up to 10 virtual users over 10 seconds, hold that load for 30 seconds, then ramp down.
    stages: [
        { duration: '10s', target: 10 }, // Ramp-up
        { duration: '30s', target: 10 }, // Steady load
        { duration: '5s', target: 0 },   // Ramp-down
    ],
    // Define thresholds for success. The test will fail if p95 latency is > 500ms or if less than 99% of requests succeed.
    thresholds: {
        'http_req_duration': ['p(95)<500'], // 95% of requests must be faster than 500ms
        'checks': ['rate>0.99'], // 99% of checks must pass
    },
};

// The setup() function runs ONCE before any virtual users start.
// It's perfect for logging in and getting a token.
export function setup() {
    console.log('Logging in to get authentication token...');

    // Make sure you have registered this user in your new, clean database.
    const loginPayload = JSON.stringify({
        username: 'k6user',
        password: 'password123',
    });

    const loginRes = http.post(
        `${BASE_URL}/api/auth/login`,
        loginPayload,
        { headers: { 'Content-Type': 'application/json' } }
    );

    // Check if login was successful and extract the token.
    check(loginRes, { 'login successful': (r) => r.status === 200 });
    const token = loginRes.json('token');
    console.log('Login successful. Token received.');

    // The token returned here will be passed to the default function.
    return token;
}

// The default function is the main code that each Virtual User (VU) will run in a loop.
// The 'token' parameter comes directly from the return value of setup().
export default function (token) {
    // Define the headers, including the Authorization header with our shared token.
    const headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
    };

    // VU Action 1: Create a new task
    const postPayload = JSON.stringify({
        task: `k6 test task - VU ${__VU} ITER ${__ITER}`,
        done: false,
    });
    const postRes = http.post(`${BASE_URL}/api/todo-tasks`, postPayload, { headers: headers });
    check(postRes, { 'POST /api/todo-tasks is 201': (r) => r.status === 201 });

    // Wait a moment before the next action
    sleep(1);

    // VU Action 2: Get all tasks (paginated)
    const getRes = http.get(`${BASE_URL}/api/todo-tasks?page=0&size=10`, { headers: headers });
    check(getRes, { 'GET /api/todo-tasks is 200': (r) => r.status === 200 });

    // Wait another moment before the loop repeats
    sleep(1);
}