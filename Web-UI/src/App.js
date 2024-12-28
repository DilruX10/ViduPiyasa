import RoutesComponent from "./routes/RoutesComponent";
import { store } from "./actions/store";
import { Provider } from "react-redux";

function App() {
  return (
    <Provider store={store}>
      <RoutesComponent />
    </Provider>
  );
}

export default App;
