import axios from 'axios';

export const api = axios.create({
    baseURL: "http://leodelmiro-dscatalog.herokuapp.com/"
});