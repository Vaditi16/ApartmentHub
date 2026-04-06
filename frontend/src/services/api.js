const BASE_URL = 'http://localhost:8082';

export const api = {
  // Flats
  getFlats: () => fetch(`${BASE_URL}/flats`).then(res => res.json()),
  getFlatsAdmin: () => fetch(`${BASE_URL}/flats/admin-view`).then(res => res.json()),
  addFlat: (data) => fetch(`${BASE_URL}/flats`, { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data) }).then(res => res.json()),
  deleteFlat: (id) => fetch(`${BASE_URL}/flats/${id}`, { method: 'DELETE' }),

  // Residents
  getResidents: () => fetch(`${BASE_URL}/residents`).then(res => res.json()),
  saveResident: (data) => fetch(`${BASE_URL}/residents`, { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data) }).then(res => res.json()),
  deleteResident: (id) => fetch(`${BASE_URL}/residents/${id}`, { method: 'DELETE' }),

  // Notices
  getNotices: () => fetch(`${BASE_URL}/notices`).then(res => res.json()),
  addNotice: (data) => fetch(`${BASE_URL}/notices`, { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data) }).then(res => res.json()),
  deleteNotice: (id) => fetch(`${BASE_URL}/notices/${id}`, { method: 'DELETE' }),

  // Issues
  getIssues: () => fetch(`${BASE_URL}/issues`).then(res => res.json()),
  addIssue: (residentId, data) => fetch(`${BASE_URL}/issues/${residentId}`, { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data) }).then(res => res.json()),
  updateIssueStatus: (id, status) => fetch(`${BASE_URL}/issues/${id}/status?status=${status}`, { method: 'PUT' }).then(res => res.json()),

  // Maintenance
  getAllMaintenance: () => fetch(`${BASE_URL}/maintenance`).then(res => res.json()),
  getMaintenanceByFlat: (flatId) => fetch(`${BASE_URL}/maintenance/flat/${flatId}`).then(res => res.json()),
  addMaintenance: (flatId, data) => fetch(`${BASE_URL}/maintenance/flat/${flatId}`, { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(data) }).then(res => res.json()),
  payMaintenance: (id) => fetch(`${BASE_URL}/maintenance/pay/${id}`, { method: 'PUT' }).then(res => res.json())
};
