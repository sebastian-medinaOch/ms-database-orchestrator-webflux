import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
  scenarios: {
    normal: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: "normal",
      gracefulStop: '0s',
    },
    notFound: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: "notFound",
      gracefulStop: '0s',
      startTime: '10s'
    },
    error: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: "error",
      gracefulStop: '0s',
      startTime: '20s'
    },
    slow: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: "slow",
      gracefulStop: '10s',
      startTime: '30s'
    },
    verySlow: {
      executor: 'constant-vus',
      vus: 200,
      duration: '10s',
      exec: "verySlow",
      gracefulStop: '30s',
      startTime: '50s'
    }
  }
};

const host = "http://host.docker.internal:5000";

export function normal() {
  http.get(host + "/product/1/similarids", getHeaders());
  sleep(0.5);
}

export function slow() {
  http.get(host + "/product/2/similarids", getHeaders());
  sleep(0.5);
}

export function verySlow() {
  http.get(host + "/product/3/similarids", getHeaders());
  sleep(0.5);
}

export function notFound() {
  http.get(host + "/product/4/similarids", getHeaders());
  sleep(0.5);
}

export function error() {
  http.get(host + "/product/5/similarids", getHeaders());
  sleep(0.5);
}

function generateUUID() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    let r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
    return v.toString(16);
  });
}

function getHeaders() {
  return {
    headers: {
      "messageId": generateUUID(), // Generar un ID único por cada request
      "Content-Type": "application/json"
    }
  };
}
