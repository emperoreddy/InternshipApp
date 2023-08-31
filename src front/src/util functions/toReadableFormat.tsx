function toReadableFormat(property: string): string {
  // Convert camelCase or snake_case to Human Readable Format
  return property
    .replace(/([A-Z])/g, " $1") // Convert camelCase to spaced
    .replace(/_/g, " ") // Convert snake_case to spaced
    .replace(/^\w/, (c) => c.toUpperCase()); // Capitalize first letter
}

export default toReadableFormat;