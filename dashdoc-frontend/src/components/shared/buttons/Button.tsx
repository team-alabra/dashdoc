import React from 'react';
import styled from 'styled-components';
import MaterialButton from '@mui/material/Button';

export const StyledButton = styled(MaterialButton)<{
  customColor?: string;
}>`
  backgroundColor: ${({ customColor }) => customColor};
  color: white;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'DM Sans';
  width: fit-content;
  font-size: 13px !important;
`;

const Button: React.FC<any> = ({ ...props }: any) => {
  return <StyledButton {...props}>{props.children}</StyledButton>;
};

export default Button;
