import React from 'react'
import { PricingCard } from '@shared/containers/PricingCard';
import { SwitchSlider } from '@shared/buttons/SwitchSlider';
import { PricingTheme } from '@typings/pricing';
import * as S from '@styles';
import { usePlan } from '@hooks';
import { PLAN_IMAGES, PLAN_TERM_SLIDER_OPTIONS } from '@constants';

const SubscriptionPlans: React.FC<any> = (props) => {
  const { currentTerm, setCurrentTerm, allPlans } = usePlan();

  return (
      <S.SubscriptionContainer data-testid="subscription-home">
        <S.StyledSwitchTitle>Plan Term Options</S.StyledSwitchTitle>
        <SwitchSlider className='plan-type-styles' options={PLAN_TERM_SLIDER_OPTIONS} switchId="planterm-toggle-switch" theme={'var(--light-blue)'} onChange={setCurrentTerm}/>
        <S.SubscriptionCardsContainer>
          <PricingCard id="sole-provider-plan" imgPath={PLAN_IMAGES.solePractice} theme={PricingTheme.light_blue} title='Solo Practice' font_color='var(--light-blue)' planDetail={allPlans.soleProviderPlans[currentTerm]} term={currentTerm}/>
          <PricingCard id="small-practice-plan" imgPath={PLAN_IMAGES.smallPractice} theme={PricingTheme.bright_blue} title='Group Practice' font_color='var(--bright-blue)' planDetail={allPlans.smallAgencyPlans[currentTerm]} term={currentTerm}/>
          <PricingCard id="medium-practice-plan" imgPath={PLAN_IMAGES.mediumPractice} theme={PricingTheme.purple} title='Group Practice +' font_color='var(--purple)' planDetail={allPlans.mediumAgencyPlans[currentTerm]} term={currentTerm}/>
          <PricingCard id="large-practice-plan" imgPath={PLAN_IMAGES.largePractice} theme={PricingTheme.purple} title='Enterprise' font_color='var(--purple)' planDetail={allPlans.largeAgencyPlans[currentTerm]} term={currentTerm}/>
        </S.SubscriptionCardsContainer>
      </S.SubscriptionContainer>
  )
}


export default SubscriptionPlans;
