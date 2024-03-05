/**
 * @jest-environment jsdom
 */
import React from 'react';
import 'jest-styled-components';
import { screen } from '@testing-library/dom';
import { render } from '@testing-library/react';
import Footer from './Footer';

describe('Footer', () => {
  it('renders the element and displays appropriate text', async () => {
    render(<Footer>2023 Dash Doc Now</Footer>);
    const footer = await screen.findByText('2023 Dash Doc Now');
    expect(footer).toBeDefined();
  });
});
