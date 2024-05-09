import { Button, Flex, Grid, GridItem, Heading, Text } from "@chakra-ui/react";
import { Link } from "react-router-dom";

const Widget = (props) => {
  const deleteWidget = async (widgetName) => {
    await fetch("http://localhost:8080/api/widgets/" + widgetName, {
      method: 'delete'
    })
      .then(() => props.getWidgets())
      .catch((error) => console.error(error));
  };

  return (
    <Grid
      templateColumns={["repeat(1, 1fr)", "repeat(2, 1fr)", "repeat(4, 1fr)"]}
    >
      {props.widgets.map((widget, index) => (
        <GridItem
          key={index}
          p={5}
          boxShadow={"xl"}
          borderRadius={"md"}
          width={"60"}
          bg={"blackAlpha.800"}
          m={"4"}
        >
          <Heading
            mt={"4"}
            fontSize={"xl"}
            textAlign={"center"}
            color={"white"}
          >
            {widget.name.toUpperCase()}
          </Heading>
          <Text mt={2} p={2} color={"white"} fontSize={"medium"}>
            {widget.description}
          </Text>
          <Text p={2} color={"green"} fontSize={"medium"} fontWeight={"bold"}>
            $ {widget.price}
          </Text>
          <Flex justifyContent={"center"}>
            <Button
              m={"2"}
              colorScheme="yellow"
              as={Link}
              to={"/updateWidget/"}
              onClick={() => {
                localStorage.setItem('widgetName', widget.name)
                localStorage.setItem('widgetDescription', widget.description)
                localStorage.setItem('widgetPrice', widget.price)
                localStorage.setItem('isUpdated', true);
              }}
            >
              Update
            </Button>
            <Button
              m={"2"}
              colorScheme="red"
              onClick={() => deleteWidget(widget.name)}
            >
              Delete
            </Button>
          </Flex>
        </GridItem>
      ))}
    </Grid>
  );
};

export default Widget;
