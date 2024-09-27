import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';
import Cookie from 'js-cookie';

// Adds basic headers
axios.interceptors.request.use((config: AxiosRequestConfig) => {
  return config;
});

// Injects token on every request
axios.interceptors.request.use(injectToken, (error) => Promise.reject(error));
axios.interceptors.response.use(
  (response) => response.data,
  (error) => {
    if (error?.response?.status >= 500) {
      throw new Error(
        'Internal Server Error. Please contact customer service.'
      );
    }

    return Promise.reject(error);
  }
);

// POST method
export async function post<T, K>(
  path: string,
  payload: T,
  config?: any
): Promise<K> {
  const response: any = config
    ? await axios.post(path, payload, config)
    : await axios.post(path, payload);

  return response as K;
}

// GET method
export async function get<T>(path: string): Promise<T> {
  const response: AxiosResponse<T> = await axios.get(path);
  return response as T;
}

// PUT method
export async function put<T>(path: string, payload?: T): Promise<T> {
  const response: AxiosResponse<T> = await axios.put(path, payload);

  return response as T;
}

function injectToken(config: AxiosRequestConfig): AxiosRequestConfig {
  try {
    const token = Cookie.get('access_token');

    config.headers = {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    };

    if (token != null) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  } catch (error) {
    throw new Error(error);
  }
}
