import { PlanTypes } from '@typings/plan';
import { post, get } from '@utils/http';

export const getPlansList = async (): Promise<PlanTypes> => {
  const res: PlanTypes = await get('/api/plan/all-plans');

  return res;
}
