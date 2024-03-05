import { useEffect, useState } from "react";
import { PlanTermDetail } from "@typings/plan";
import { getPlansList } from "@api/plan";
import { useDispatch, useSelector } from "./hooks";
import { getPlans, savePlan } from "@services/slices/planSlice";


export const usePlan = () => {
  const [currentTerm, setCurrentTerm] = useState<keyof PlanTermDetail>("monthly");
  const dispatch = useDispatch();
  const allPlans = useSelector(getPlans);

  useEffect(() => {
      async function initialize() {
        const data = await getPlansList();
        dispatch(savePlan(data));
      };

      initialize();
  }, [])

  return {
    currentTerm,
    setCurrentTerm,
    allPlans
  }
}
