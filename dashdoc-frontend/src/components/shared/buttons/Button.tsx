import React from 'react';
import styled from 'styled-components';
import MaterialButton from '@mui/material/Button';

export const StyledButton = styled(MaterialButton)<{
  customColor?: string;
}>`
  background-color: ${({ customColor }) => customColor} !important;
  border-radius: 10px;
  cursor: pointer;
  font-size: 0.9em;
  font-family: 'DM Sans';
  width: fit-content;
  font-size: 13px !important;
`;

const Button: React.FC<any> = ({ ...props }: any) => {
  return <StyledButton {...props}>{props.children}</StyledButton>;
};

export default Button;
