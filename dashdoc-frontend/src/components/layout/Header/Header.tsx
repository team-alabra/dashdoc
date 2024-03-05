import React from 'react';
import styled from 'styled-components';

export const StyledHeader =  styled(
  ({ fontSize, fontColor, text, children, ...props }: any) => (
    <p {...props}>{children}</p>
  )
)`
  font-size: ${({ fontSize }) => fontSize}px;
  color: ${({ fontColor }) => fontColor}px;
`;


const Header: React.FC<any> = ({ ...props }: any) => {
  return <StyledHeader {...props}>{props.children}</StyledHeader>;
};

export default Header;
