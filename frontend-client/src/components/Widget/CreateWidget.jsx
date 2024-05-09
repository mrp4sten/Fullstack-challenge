import {
  Alert,
  AlertDescription,
  AlertIcon,
  AlertTitle,
  Box,
  Button,
  CloseButton,
  Flex,
  FormControl,
  FormLabel,
  Heading,
  Input,
} from "@chakra-ui/react";
import { useEffect, useState } from "react";

const CreateWidget = () => {
  const [isUpdated, setIsUpdated] = useState(false);
  const [nameWidget, setNameWidget] = useState("");
  const [descriptionWidget, setDescriptionWidget] = useState("");
  const [priceWidget, setPriceWidget] = useState(0);
  const [display, setDisplay] = useState("none");
  const [status, setStatus] = useState("success");
  const [title, setTitle] = useState("exmple");
  const [description, setDescription] = useState("example");

  const url = "http://localhost:8080/api/widgets";

  const onChangeNameWidget = (e) => {
    setNameWidget(e.target.value);
  };

  const onChangeDescriptionWidget = (e) => {
    setDescriptionWidget(e.target.value);
  };

  const onChangePriceWidget = (e) => {
    setPriceWidget(e.target.value);
  };


  const onSubmit = async (e) => {
    e.preventDefault();

    const newWidget = {
      name: nameWidget,
      description: descriptionWidget,
      price: priceWidget
    };

    const res = await fetch(isUpdated ? `${url}/${nameWidget}` : url, {
      method: isUpdated ? 'PUT' : "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newWidget),
    });

    switch (res.status) {
      case 201:
        setDisplay("");
        setStatus("success");
        setTitle("Success!");
        setDescription("Widget was created successfully");
        break;
      case 200:
        setDisplay("");
        setStatus("success");
        setTitle("Success!");
        setDescription("Widget was updated successfully");
        break;
      default:
        setDisplay("");
        setStatus("error");
        setTitle("Error!");
        setDescription("Widget was not created");
        break;
    }

    localStorage.clear()
    setIsUpdated(false)
  };

  useEffect(() => {
    setIsUpdated(localStorage.getItem('isUpdated'))
    setNameWidget(localStorage.getItem('widgetName'))
    setDescriptionWidget(localStorage.getItem('widgetDescription'))
    setPriceWidget(localStorage.getItem('widgetPrice'))
  }, []);

  return (
    <>
      <Flex justifyContent={"center"} alignItems={"center"}>
        <Box
          p={5}
          boxShadow={"xl"}
          borderRadius={"md"}
          width={"96"}
          bg={"blackAlpha.800"}
          mt={"4"}
        >
          <Heading
            mt={"4"}
            fontSize={"xl"}
            textAlign={"center"}
            color={"white"}
          >
            {localStorage.getItem('isUpdated') ? 'Update Widget' : 'Create Widget'}
          </Heading>
          <form onSubmit={onSubmit}>
            <FormControl mt={"4"} color={"white"}>
              <FormLabel htmlFor="nameWidget">Name</FormLabel>
              <Input
                id="nameWidget"
                name="nameWidget"
                onChange={onChangeNameWidget}
                value={isUpdated ? nameWidget : ''}
              ></Input>
              <FormLabel htmlFor="descriptionWidget">Description</FormLabel>
              <Input
                id="descriptionWidget"
                name="descriptionWidget"
                onChange={onChangeDescriptionWidget}
                value={isUpdated ? descriptionWidget : ''}
              ></Input>
              <FormLabel htmlFor="priceWidget">Price</FormLabel>
              <Input
                id="priceWidget"
                name="priceWidget"
                type="number"
                onChange={onChangePriceWidget}
                value={isUpdated ? priceWidget : ''}
              ></Input>
              <Button colorScheme="teal" type={"submit"} mt={"2"}>
                Submit
              </Button>
            </FormControl>
          </form>
        </Box>
      </Flex>
      <Flex mt={"4"} justifyContent={"center"} alignItems={"center"}>
        <Box display={display}>
          <Alert
            status={status}
            w={"90"}
            h={"10"}
            flexDirection={"row"}
            borderRadius={"full"}
          >
            <Flex flexDirection={"row"}>
              <AlertIcon />
              <AlertTitle>{title}</AlertTitle>
              <AlertDescription>{description}</AlertDescription>
            </Flex>
            <Box>
              <CloseButton
                alignSelf="flex-start"
                position="relative"
                right={-1}
                top={-1}
                onClick={() => setDisplay("none")}
              />
            </Box>
          </Alert>
        </Box>
      </Flex>
    </>
  );
};

export default CreateWidget;
