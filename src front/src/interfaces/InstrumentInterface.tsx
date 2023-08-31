export default interface Instrument {
  id: number;
  issuerId: number;
  isin: string;
  currency: string;
  type: string;
  description: string;
  effectiveDate: Date;
  status: string;
}
