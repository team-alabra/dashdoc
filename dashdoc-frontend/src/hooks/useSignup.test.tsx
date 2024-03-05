import 'jest-styled-components';
import { act, renderHook, waitFor } from '@testing-library/react';
import { wrapper } from '@tests/renderWithProps';
import { useSignup } from './useSignup';
import axios from 'axios';
import {
  SOLE_PROVIDER_RESPONSE,
  MOCK_SOLE_PROVIDER_REQUEST,
} from '@utils/mocks/signupMocks';
import * as useSignupHook from '@hooks/useSignup';

jest.mock('axios');

beforeEach(() => {
  jest.clearAllMocks();
});

describe('useSignUp hook', () => {
  it('makes a successful API request and returns the newly signed up customer', async () => {
    // Arrange
    const myMock = jest.fn();
    const mockResponse = myMock.mockResolvedValue(SOLE_PROVIDER_RESPONSE);

    const { result } = renderHook(() => useSignup(), { wrapper });

    // Act
    await act(() => result.current.signupHandler(MOCK_SOLE_PROVIDER_REQUEST));
    act(() => result.current.setCustomer(mockResponse));

    expect(myMock).toBeCalled();
    waitFor(() => expect(window.location.href).toBe('http://localhost/emailVerify'))
   ;
  });
});
