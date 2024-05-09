import { Flex } from "@chakra-ui/react";
import { useEffect, useState } from "react";
import Widget from "./Widget";

const ListWidget = () => {
  const [widgetList, setWidget] = useState([]);

  const url = "http://localhost:8080/api/widgets";

  const fetchWidgets = async (urlApi) => {
    await fetch(urlApi)
      .then((response) => response.json())
      .then((data) => setWidget(data))
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    fetchWidgets(url);
  }, []);

  const getWidgets = () => {
    fetchWidgets(url);
  };

  return (
    <Flex justifyContent={"center"}>
      <Widget widgets={widgetList} getWidgets={getWidgets}/>
    </Flex>
  );
};

export default ListWidget;
