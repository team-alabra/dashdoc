import { Plan } from "./plan";

export type PricingStyleProps = {
  background_color?: PricingTheme
  font_color?: string;
};

export type PricingCardProps = {
  id: string;
  theme: PricingTheme;
  font_color?: string;
  title?: string;
  features?: string[];
  planDetail: Plan;
  term: string;
  userType?: string;
  imgPath?: any;
}

export type PriceDetails = {
  [term: string]: string;
}

export enum PriceTiers {
  BASE,
  MID,
  PREMIUM
}

export enum PricingTheme  {
  bright_blue = 'var(--bright-blue)',
  purple = 'var(--purple)',
  light_blue = 'var(--light-blue)',
  bright_blue_gradient = 'var(--bright-blue-gradient)',
  purple_gradient = 'var(--purple-gradient)',
  light_blue_gradient = 'var(--light-blue-gradient)',
};

