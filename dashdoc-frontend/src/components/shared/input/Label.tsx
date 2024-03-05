import React from 'react';
import { LabelProps } from '@typings/input';

export const Label: React.FC<LabelProps> = ({ label, className, htmlFor }) => {
  return (
    <>
      <label
        htmlFor={htmlFor}
        className={className}
      >
        {label}
      </label>
    </>
  );
};

export default Label;
