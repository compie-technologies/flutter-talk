import moment from "moment";
import {constants} from "./consts";

export async function getMovies(page) {
    const params = {
      'api_key': '1f2e9419c5a0c7c4cbe20f4e07fe4859',
      'primary_release_date.gte': moment().add(-9, 'days').format('YYYY-MM-DD'),
      'primary_release_date.lte': moment().add(9, 'days').format('YYYY-MM-DD'),
      'page': String(page),
    };

    let response = await fetch(constants.baseUrl + _attachParams('/discover/movie', params))
    if (response.status !== 200) {
        throw(response);
    }
    return response;
}

export async function getGenres() {
    const params = {
        'api_key': '1f2e9419c5a0c7c4cbe20f4e07fe4859',
    };

    let response = await fetch(constants.baseUrl + _attachParams('/genre/movie/list', params))
    if (response.status !== 200) {
        throw(response);
    }
    return response;
}

function _createParamString(key, value) {
    return key + "=" + value;
}

function _attachParams(originalRoute, params) {
    if (params != null) {
        let index = 0;
        for (let param in params) {
            if (params.hasOwnProperty(param)) {
                originalRoute += index === 0 ? '?' : '&';
                originalRoute += _createParamString(param, params[param]);
                index++;
            }
        }
    }
    return originalRoute;
}