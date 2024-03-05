import React, { useState } from "react";
import * as S from "@styles";

type SliderOptions = {
  id: string;
  label: string;
  value: string;
  aria_label?: string;
};

export const SwitchSlider: React.FC<{
  options: SliderOptions[];
  switchId: string;
  theme?: string;
  className?: string;
  onChange: React.Dispatch<React.SetStateAction<string>>;
}> = (props) => {
  const [checked, setChecked] = useState<string>(props.options[0]?.value);

  const handleChange = (
    event: React.MouseEvent<HTMLElement>,
    newChoice: string | null,
  ) => {
    if (newChoice !== null) {
      props.onChange(newChoice)
      setChecked(newChoice);
    }
  };

  return (
      <S.StyledToggleGroup
        value={checked}
        exclusive
        onChange={handleChange}
        aria-label="plan term"
        className={props.className}
        data-testid="plan-term-slider"
      >
        {props.options.map((el, idx) => (
          <S.StyledToggleButton data-testid={`${el.value}-button`} themecolor={props.theme} key={`${el.value}-${idx}`} value={el.value}>{el.label}</S.StyledToggleButton>
        ))}
      </S.StyledToggleGroup>
  );
};

