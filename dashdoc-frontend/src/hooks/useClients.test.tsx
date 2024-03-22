import 'jest-styled-components';
import { act, renderHook } from '@testing-library/react';
import { wrapper } from '@tests/renderWithProps';
import { usePlan } from './usePlan';
import MockAdapter from 'axios-mock-adapter';
import axios from 'axios';
import { mockPlansResponse } from '@tests/mocks/mockData';

jest.mock('axios');

beforeEach(() => {
  jest.clearAllMocks();
});

describe('useClients hook', () => {
  const axiosMock = new MockAdapter(axios);

  it('allows us to update current term', () => {
    // Arrange
    
    // Act
    
    // Assert

  });

  it('returns allPlans object', () => {
    // Arrange

    // Act


    // Assert

  });

  it('returns allPlans object', () => {
    // Arrange

    // Act


    // Assert

  });

  it('returns allPlans object', () => {
    // Arrange

    // Act


    // Assert

  });

  it('returns allPlans object', () => {
    // Arrange

    // Act


    // Assert

  });
});