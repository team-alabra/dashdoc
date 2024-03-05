export const MOCK_SOLE_PROVIDER_REQUEST = {
  userType: 'SOLE_PROVIDER',
  email: 'jane@email.com',
  password: 'Hello123@',
  firstName: 'Jane',
  lastName: 'Smith',
};

export const MOCK_AGENCY_PROVIDER_REQUEST = {
  userType: 'AGENCY_PROVIDER',
  agencyCode: '123abc',
  email: 'agency@email.com',
  password: 'Hello123@',
  firstName: 'Johnny',
  lastName: 'Bravo',
};

export const MOCK_AGENCY_ADMINISTRATOR_REQUEST = {
  userType: 'AGENCY_ADMININSTRATOR',
  agencyName: 'Alabra Agency',
  email: 'speechadmin@email.com',
  password: 'Hello123@',
  firstName: 'Joe',
  lastName: 'Smith',
};

export const SOLE_PROVIDER_RESPONSE = {
  ...MOCK_SOLE_PROVIDER_REQUEST,
  id: 1,
};

export const AGENCY_PROVIDER_RESPONSE = {
  ...MOCK_AGENCY_PROVIDER_REQUEST,
  id: 2,
};

export const AGENCY_ADMIN_RESPONSE = {
  ...MOCK_AGENCY_ADMINISTRATOR_REQUEST,
  id: 3,
};
