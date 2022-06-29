import { Pipe, PipeTransform } from '@angular/core';
import { Tool } from '../models/tool';

@Pipe({
  name: 'toolPresent'
})
export class ToolPresentPipe implements PipeTransform {

  transform(original: any[], tool: Tool): any[] {
    let sorted: any[] = [];

    for (let i = 0; i < original.length; i++) {
      let element = original[i];
      let id = element.tool.id;
      if (id === tool.id)         {
        sorted.push(element);
      }
    }
  return sorted;
  }

}
