/**
 * @jest-environment jsdom
 */
import React from 'react';
import '@testing-library/jest-dom';
import 'jest-styled-components';
import { screen, fireEvent, waitFor, getByText } from '@testing-library/dom';
import { act, render } from '@testing-library/react';
import Navbar from './Navbar';
import { createMemoryHistory } from 'history';
import { Router, Link } from 'react-router-dom';
import { renderWithProvider } from '@tests/renderWithProps';

describe('Navbar', () => {
  it('should render the component', () => {
    renderWithProvider(<Navbar />, { route: '/' });
    const componentDiv = screen.getByTestId('nav-bar-container');
    expect(componentDiv).toBeDefined();
  });

  it('should navigate user to appropriate location when link is clicked', async () => {
    
    renderWithProvider(<Navbar/>);

    await act(async () => fireEvent.click(screen.getByText('Clients')));

    expect(window.location.href).toContain("/clients");
  });
});
