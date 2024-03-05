import React from 'react';
import { InputProps } from '@typings/input';

export const Input: React.FC<InputProps> = ({
  id,
  name,
  isChecked,
  type = 'text',
  value,
  onChange,
  className,
  required,
  placeholder,
}) => {
  return (
    <>
      <input
        className={className}
        name={name}
        id={id}
        type={type}
        value={value}
        checked={isChecked}
        onChange={onChange}
        required={required}
        placeholder={placeholder}
      />
    </>
  );
};
