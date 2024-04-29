import { ToggleButton, ToggleButtonGroup } from '@mui/material';
import styled from 'styled-components';
import React from 'react';

export const StyledToggleGroup = styled(ToggleButtonGroup)`
  margin: auto;
  padding-left: 10px;
  padding-right: 10px;
  height: fit-content !important;
  width: fit-content;
  border-radius: 10px;

  & :last-child {
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
  }

  & :first-child {
    border-top-left-radius: 15px;
    border-bottom-left-radius: 15px;
  }
`;

export const StyledToggleButton = styled(ToggleButton)<{ themecolor?: string }>`
  text-transform: none !important;
  font-size: medium !important;

  &.Mui-selected {
    color: white !important;
    background: var(--light-blue) !important;
  }

  &.Mui-selected:hover {
    background: var(--light-blue);
    filter: brightness(95%);
  }
`;

export const StyledSwitchTitle = styled.div`
  margin: auto;
  margin-bottom: 10px;
  margin-top: 2%;
`;

export const StyledContainer = styled(
  ({ customWidth, customHeight, children, ...props }: any) => (
    <div {...props}>{children}</div>
  )
)`
  width: ${({ customWidth }) => customWidth}px;
  height: ${({ customHeight }) => customHeight}px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
`;

export const StyledHeader = styled(
  ({
    fontSize,
    fontColor,
    margin,
    fontWeight,
    children,
    ...props
  }: any) => <p {...props}>{children}</p>
)`
  font-size: ${({ fontSize }) => fontSize}px;
  color: ${({ fontColor }) => fontColor};
  margin: ${({ margin }) => margin}px;
  font-weight: ${({ fontWeight }) => fontWeight};
`;
