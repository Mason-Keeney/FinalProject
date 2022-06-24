export class ToolCondition {
  id: number | null;
  name: string | null;

  constructor(id: number | null = 0, name: string | null = '') {
    this.id = id;
    this.name = name;
  }
}
