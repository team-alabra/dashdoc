import React from 'react';
import styled from 'styled-components';

export const StyledFooter = styled.footer<{
  text?: string;
}>`
position: fixed;
border-top: 1px solid rgba(0, 0, 0, 0.2);
bottom: 0;
left: 0;
right: 0;
text-align: center;
font-size: 1rem;
padding: 1rem;
`;

const Footer: React.FC<any> = ({ ...props }: any) => {
  return <StyledFooter {...props}>{props.children}</StyledFooter>;
};

export default Footer;
