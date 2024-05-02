import { Analytics } from '@typings/analytics';
import { get } from '@utils/http';
import { mockUserAnalytics } from '@utils/mocks/analyticsMocks';

// gather user analytics from token
export const userAnalytics = async (): Promise<any> => {
  try {
    // return await get(`/api/user/analytics`);
    return mockUserAnalytics;
  } catch (error) {
    throw error;
  }
};
