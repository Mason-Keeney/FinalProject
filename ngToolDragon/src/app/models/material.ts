export class Material {
  id: number | null;
  name: string | null;
  description: string | null;
  active: boolean;

  constructor(
    id: number | null = 0,
    name: string | null = '',
    description: string | null = '',
    active: boolean = true
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.active = active;
  }
}
