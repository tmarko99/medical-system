export interface PageDto<T> {
  content: T[];
  pageSize: number;
  totalElements: number;
  pageNumber: number;
}
