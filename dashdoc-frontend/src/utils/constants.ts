import { NavbarData } from '@typings/navbarData';
import { PriceDetails } from '@typings/pricing';

// NAVIGATION
export const NAVBAR_DATA: NavbarData[] = [
  {
    title: 'About Us',
    to: '/aboutUs',
  },
  {
    title: 'Pricing',
    to: '/subscriptionPlans',
  },
  {
    title: 'Explore Free Trial',
    to: '/freeTrial',
  },
  {
    title: 'Contact Us',
    to: '/contactUs',
  },
  {
    title: 'Login',
    to: '/login',
  },
];

export const PROTECTED_NAVBAR_DATA = [
  {
    title: 'Document Library',
    to: '/dashboard/documents',
  },
  {
    title: 'Billing',
    to: '/dashboard/billing',
  },
  {
    title: 'My Account',
    to: '/dashboard/myAccount',
  },
];

// PRICING AND PLANS
export const PLAN_FEATURES_INDIVIDUAL = [
  { feature: 'Document generation', tier: 'base' },
  { feature: 'Client management', tier: 'base' },
  { feature: 'Free templates', tier: 'base' },
  { feature: 'Client commuication', tier: 'base' },
  { feature: 'Billing platform and invoicing', tier: 'premium' },
  { feature: 'Analytics dashboard', tier: 'premium' },
];

export const PLAN_TERM_SLIDER_OPTIONS = [
  {
    name: 'switchPlanTerm',
    label: 'Monthly',
    value: 'monthly',
    id: 'switchMonthly',
    checked: true,
  },
  {
    name: 'switchPlanTerm',
    label: 'Annual',
    value: 'yearly',
    id: 'switchYearly',
  },
];

export const PLAN_TYPE_SLIDER_OPTIONS = [
  {
    name: 'switchPlanType',
    label: 'Sole Provider',
    value: 'soleProvider',
    id: 'soleProvider',
    checked: true,
  },
  {
    name: 'switchPlanType',
    label: 'Agency Providers',
    value: 'agencySmall',
    id: 'agencySmall',
  },
];

export const PLAN_TERM_LABEL: PriceDetails = {
  monthly: ' per month',
  yearly: ' per year',
};

export const PLAN_IMAGES = {
  solePractice: `${process.env.REACT_APP_S3_BUCKET}/pricing/sole_practice.svg`,
  smallPractice: `${process.env.REACT_APP_S3_BUCKET}/pricing/small_practice.svg`,
  mediumPractice: `${process.env.REACT_APP_S3_BUCKET}/pricing/medium_practice.svg`,
  largePractice: `${process.env.REACT_APP_S3_BUCKET}/pricing/enterprise_practice.svg`,
};

export const LOGIN_MAIN_IMAGE = `${process.env.REACT_APP_S3_BUCKET}/login/carrying_papers.svg`;

export const SIGNUP_USER_OPTIONS = [
  {
    name: 'switchUserType',
    label: 'Sole Provider',
    value: 'soleProvider',
    id: 'switchSoleProvider',
    checked: true,
  },
  {
    name: 'switchUserType',
    label: 'Agency Provider',
    value: 'agencyProvider',
    id: 'switchAgencyProvider',
    checked: true,
  },
];
