export const formatCurrency = (number: number, symbol = '$') => {
  // Add thousands separator
  let formattedNumber = number
    .toFixed(2)
    .toString()
    .replace(/\B(?=(\d{3})+(?!\d))/g, ',');

  // Format the number as a currency string
  return `${symbol}${formattedNumber}`;
};
