import React from "react";
import * as S from "@styles";
import { PricingCardProps, PricingTheme } from "@typings/pricing";
import Button from "@shared/buttons/Button"
import { PLAN_FEATURES_INDIVIDUAL, PLAN_TERM_LABEL } from "@constants";
import { CheckCircle, Cancel } from "@mui/icons-material";
import numeral from "numeral";

export const PricingCard: React.FC<PricingCardProps> = (props) => {
  const { planDetail, term, imgPath, theme = PricingTheme.bright_blue } = props;
  const { price = 0, pricePerAdditionalUser = 0 } = planDetail;

  const formattedPrice: string = price > 0 ? numeral(price).format('$0,0') : "--";
  const formattedAdditionalUserPrice: string = !!pricePerAdditionalUser ? numeral(pricePerAdditionalUser).format('$0,0') : "--";

  return (
    <S.PlanContainer data-testid='pricing-card'>
      <S.PlanHeader>
        <S.PlanTitle background_color={theme}>
          <p> {props.title}</p>
        </S.PlanTitle>
        <S.PlanIcon>
          <img src={imgPath ?? null} alt="Sole Provider"/>
        </S.PlanIcon>
      </S.PlanHeader>
      <S.PlanContent>
        {PLAN_FEATURES_INDIVIDUAL.map((p, idx) => {
          const isPremium = props.title == "Starter" && p.tier == 'premium';

          return (
            <p key={`${props.id}-feat-${idx}`}>
              {isPremium ? <Cancel color="disabled"/> : <CheckCircle color="success"/>} <span>{p.feature}</span>
            </p>
          )
      })}
      </S.PlanContent>
      <S.PlanPrice  font_color={props.font_color}>
        <div><span data-testid="plan-price" className='plan-price'>{formattedPrice}</span><span className='plan-term'>{PLAN_TERM_LABEL[term]}</span></div>
        {pricePerAdditionalUser > 0 ? <div><span>{`+ ${formattedAdditionalUserPrice} per additional user `}</span></div> : null}
      </S.PlanPrice>
      <Button color={theme} data-testid='plan-button'>
        START FREE TRIAL
      </Button>
    </S.PlanContainer>
  );
}

