import { get } from '@utils/http';

// gather user analytics from token
export const userAnalytics = async (): Promise<any> => {
  try {
    return await get(`/api/user/analytics`);
  } catch (error) {
    throw error;
  }
};
