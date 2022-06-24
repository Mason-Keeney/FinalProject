export class Address {
  id: number | null;
  street1: string | null;
  street2: string | null;
  city: string | null;
  state: string | null;
  postalCode: string | null;
  active: boolean;

  constructor(
    id: number | null = 0,
    street1: string | null = '',
    street2: string | null = '',
    city: string | null = '',
    state: string | null = '',
    postalCode: string | null = '',
    active: boolean = true
  ) {
    this.id = id;
    this.street1 = street1;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.active = active;
  }
}
