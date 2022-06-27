import { Tool } from './../models/tool';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'toolSearch'
})
export class ToolSearchPipe implements PipeTransform {

  transform(toolList: Tool[], search: string): Tool[] {
    let sorted: Tool[] = [];

    if (search == '') {
      sorted = toolList;
    } else {
      for (let i = 0; i < toolList.length; i++) {
        let tool = toolList[i];
        if (
          tool.description?.includes(search) ||
          tool.name?.includes(search) ||
          tool.owner?.firstName?.includes(search) ||
          tool.owner?.lastName?.includes(search))
          {
          sorted.push(tool);
        }
      }
    }
    return sorted;
  }
  }

