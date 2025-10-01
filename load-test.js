import http from 'k6/http';
import { check, sleep } from 'k6';

// This section defines the load profile for the test.
// We'll simulate 5 virtual users (VUs) running for 30 seconds.
export const options = {
    vus: 5,
    duration: '30s',
};

// This is the main function that each virtual user will run in a loop.
export default function () {
    const baseUrl = 'https://todo-service-400624648397.asia-south1.run.app/api/todo-tasks';
    const headers = { 'Content-Type': 'application/json' };

    // 1. Create a new task
    const postPayload = JSON.stringify({
        task: `k6 test task - ${__VU}-${__ITER}`, // Unique task name for each request
        done: false,
    });

    const postRes = http.post(baseUrl, postPayload, { headers: headers });
    check(postRes, { 'POST request is 201 Created': (r) => r.status === 201 });

    // 2. Get all tasks
    const getRes = http.get(baseUrl);
    check(getRes, { 'GET request is 200 OK': (r) => r.status === 200 });

    // Wait for 1 second before the next iteration
    sleep(1);
}