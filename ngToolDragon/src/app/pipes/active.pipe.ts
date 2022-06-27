import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'active'
})
export class ActivePipe implements PipeTransform {

  transform(original: any[]): any[] {
    let sorted: any[] = [];

    original.forEach(element => {
      if(element.active) {
        sorted.push(element);
      }
    });

    return sorted;
  }

}
