import React from 'react';
import styled from 'styled-components';

export const StyledButton = styled.button<{
  color?: string;
}>`
  background: ${({ color }) => getColor(color)};
  color: white;
  font-size: 0.9em;
  margin: 1em;
  padding: 0.25em 0.75em;
  width: 150px;
  height: 50px;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  font-family: 'DM Sans';
  width: fit-content;
`;

const getColor = (choice: string) => {
  switch(choice){
    case 'primary':
      return "#fd7702";
    case 'secondary':
      return '#1D44B8';
    default:
      return choice;
  }
}
const Button: React.FC<any> = ({ ...props }: any) => {
  return <StyledButton {...props}>{props.children}</StyledButton>;
};

export default Button;
