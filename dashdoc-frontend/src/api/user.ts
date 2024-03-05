import { User } from '@typings/user';
import { get, put } from '@utils/http';

export const fetchUser = async (id: number): Promise<User> => {
    return await get(`/api/user/${id}`);
};

export const updateUser = async (payload: User): Promise<User> => {
    return await put(`/api/user/update`, payload);
};
