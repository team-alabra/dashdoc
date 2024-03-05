export type InputProps = {
  id?: string;
  name?: string;
  isChecked?: boolean;
  type: string;
  value: string;
  label?: string;
  onChange: any;
  className: string;
  required?: boolean,
  placeholder?: string,
};


export type LabelProps = {
  id?: string,
  label: string,
  className: string,
  htmlFor?: string
}


export type RadioProps = {
  options: any,
  value: any,
  setValue: any,
  name?: string,
  className?: string
}
