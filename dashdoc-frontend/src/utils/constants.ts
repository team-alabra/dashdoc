import { GridColDef } from "@mui/x-data-grid";
import { PriceDetails } from "@typings/pricing";

// NAVIGATION
export const NAVBAR_ROUTES = [
  {
    title: "Document Library",
    to: "/documents",
  },
  {
    title: "Clients",
    to: "/clients",
  },
  {
    title: "Billing",
    to: "/billing",
  },
  {
    title: "My Account",
    to: "/myAccount",
  },
];

export const PLAN_TERM_SLIDER_OPTIONS = [
  {
    name: "switchPlanTerm",
    label: "Monthly",
    value: "monthly",
    id: "switchMonthly",
    checked: true,
  },
  {
    name: "switchPlanTerm",
    label: "Annual",
    value: "yearly",
    id: "switchYearly",
  },
];

export const PLAN_TYPE_SLIDER_OPTIONS = [
  {
    name: "switchPlanType",
    label: "Sole Provider",
    value: "soleProvider",
    id: "soleProvider",
    checked: true,
  },
  {
    name: "switchPlanType",
    label: "Agency Providers",
    value: "agencySmall",
    id: "agencySmall",
  },
];

export const PLAN_TERM_LABEL: PriceDetails = {
  monthly: " per month",
  yearly: " per year",
};

export const LOGIN_MAIN_IMAGE = `${process.env.REACT_APP_S3_BUCKET}/login/carrying_papers.svg`;

export const SIGNUP_USER_OPTIONS = [
  {
    name: "switchUserType",
    label: "Sole Provider",
    value: "soleProvider",
    id: "switchSoleProvider",
    checked: true,
  },
  {
    name: "switchUserType",
    label: "Agency Provider",
    value: "agencyProvider",
    id: "switchAgencyProvider",
    checked: true,
  },
];

export const userMessage: { [userType: string]: string } = {
  SOLE_PROVIDER: "Full Name",
  AGENCY_PROVIDER: "One Time Passcode",
  AGENCY_ADMINISTRATOR: "Organization or Agency Name",
};

export const defaultTableProps: Partial<GridColDef> = {
  minWidth: 100,
  headerClassName: "table-header",
  flex: 1,
  headerAlign: "center",
};

export const CLIENT_TABLE_FIELDS: GridColDef[] = [
  { field: "firstName", headerName: "First name" },
  { field: "lastName", headerName: "Last name" },
  { field: "ageGroup", headerName: "Age Group" },
  {
    field: "age",
    headerName: "Age",
    type: "number",
  },
  { field: "email", headerName: "Email" },
  { field: "phoneNUmber", headerName: "Phone Number" },
];
