import axios from 'axios';

export const api = axios.create({
    baseURL: "http://leodelmiro-dscatalog.herokuapp.com/"
});

export const TOKEN = "Basic ZHNjYXRhbG9nOmRzY2F0YWxvZzEyMw==";