export class Dictionary {

  private _id: number;
  private _addDate: Date;
  private _editDate: Date;
  private _key: string;
  private _value: string;
  private _image: string;

  constructor() {
  }

  get id(): number {
    return this._id;
  }

  get addDate(): Date {
    return this._addDate;
  }

  get editDate(): Date {
    return this._editDate;
  }

  get key(): string {
    return this._key;
  }

  get value(): string {
    return this._value;
  }

  get image(): string {
    return this._image;
  }

}
