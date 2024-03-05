import React from 'react';
import * as S from '@styles';
import { NavbarData } from '@typings/navbarData';

type NavLinksProps = {
  navbarData: NavbarData[];
};

const NavLinks = ({ navbarData }: NavLinksProps) => {
  return (
    <S.MainNav>
      {navbarData.map((link: NavbarData, idx: number) => {
        return (
          <S.NavLiElement key={idx}>
            <S.NavLink data-testid={`nav-link-${idx}`} to={link.to}>
              {link.title}
            </S.NavLink>
          </S.NavLiElement>
        );
      })}
    </S.MainNav>
  );
};

export default NavLinks;
