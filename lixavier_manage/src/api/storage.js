
import httpRequest from '@/utils/httpRequest';

export function createStorage(data) {
  return httpRequest({
    url: httpRequest.adornUrl('storage/create'),
    method: 'post',
    data: data
  })
}
