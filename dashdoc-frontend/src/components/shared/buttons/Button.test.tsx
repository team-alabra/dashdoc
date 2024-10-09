/**
 * @jest-environment jsdom
 */
import React from 'react';
import 'jest-styled-components';
import { screen } from '@testing-library/dom';
import { render } from '@testing-library/react';
import Button from './Button';

describe('Styled Button', () => {
  it('renders the element and displays appropriate text', async () => {
    render(<Button>Click Me</Button>);
    const button = await screen.findByText('Click Me');
    expect(button).toBeInTheDocument();
  });
});
