import axios from 'axios';

const STAFF_SIGNIN_BASE_URL = `http://localhost:8088/staffs`;
const ADOPTER_SIGNIN_BASE_URL = `http://localhost:8088/adopters`;

class signInService {
    checkStaff(email, password) {
        return axios.get(`${STAFF_SIGNIN_BASE_URL}/checkStaff`, {
            params: {
                email: email,
                password: password,
            }
        });
    }

    getStaff(email, password){
        return axios.get(`${STAFF_SIGNIN_BASE_URL}/getStaffByEmail`, {
            params: {
                email: email,
                password: password,
            }
        });
    }

    checkAdopter(email, password) {
        return axios.get(`${ADOPTER_SIGNIN_BASE_URL}/checkAdopter`, {
            params: {
                email: email,
                password: password,
            }
        });
    }

    getAdopter(email, password){
        return axios.get(`${ADOPTER_SIGNIN_BASE_URL}/getAdopterByEmail`, {
            params: {
                email: email,
                password: password,
            }
        });
    }
}

export default new signInService();