// 取数据
const getData = (name) => {
  return JSON.parse(sessionStorage.getItem(name));
};

// 清数据
const clearData = (name) => {
  sessionStorage.removeItem(name);
};

// 存数据
const setData = (name, data) => {
  sessionStorage.setItem(name, JSON.stringify(data));
};

export { getData, clearData, setData };
