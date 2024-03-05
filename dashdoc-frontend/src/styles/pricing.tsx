import styled from "styled-components";
import { PricingStyleProps } from '../typings/pricing';
import { Slider } from "@mui/material";

export const PlanTitle = styled.div<PricingStyleProps>`
  display: flex;
  justify-content: center;
  align-items: center;
  background: ${props => props.background_color || "var(--main-blue)"};
  width: 40%;
  border-radius: 10px;
  margin: auto;
  margin-top: 1rem;
  
  box-shadow: 3px 3px 5px 1px rgba(0, 0, 0, 0.2);

  & > p {
    color: var(--white);
    font-size: large;
  }
`;

export const PlanHeader = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const PlanIcon = styled.div`
  width: 20% ;
  min-height: 101.8px;
  max-height: 101.8px;
  margin: auto;
  margin-top: 2rem;
  margin-bottom: 0;

  & img {
    width: 100%;
  }
`;

export const PlanContent = styled.div` 
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  text-align: left;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  margin: auto;
  margin-top: 2rem;
  margin-bottom: 1rem;
  font-size: 22px;

  & p {
    font-size: 1em;
    display: flex;
    align-items: center;
    margin: 0;
    margin-bottom: 1rem;
  }

  & span {
    margin-left: 0.5rem;
  }
`;

export const PlanContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  width: 20%;
  height: 750px;
  margin-top: 0;
  margin-left: 20px;
  margin-right: 20px;
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.2) 0px 5px 8px 1px;
  border: none;

  & > button {
    margin: auto;
    margin-bottom: 3rem;
    align-self: flex-end;
  }
`;

export const PlanPrice = styled.div<{ font_color: string;}>`
  text-align: center;
  margin-top: 0.5rem;
  & .plan-price {
    font-size: 2.5rem;
    color: ${(props) => props.font_color || "black" }
  }

  & .plan-term {
    font-size: 1.5rem;
  }
`
export const SubscriptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
`;

export const SubscriptionCardsContainer = styled.div `
  display: flex;
  justify-content: center;
  margin-top: 4%;
  width: 100%;
  height: 100% !important;
`;