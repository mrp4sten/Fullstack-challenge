import { Box, Divider, Flex, Heading, Link, Text } from "@chakra-ui/react";

const Welcome = () => {
    return (
        <Flex justifyContent={"center"} alignItems={"center"}>
            <Box
                p={5}
                mt={4}
                boxShadow={"xl"}
                borderRadius={"md"}
                textAlign={"center"}
            >
                <Heading mt={"4"} fontSize="xl">
                    Manage Widgets Applications
                </Heading>
                <Text mt={"4"} textAlign={"center"}>
                    Welcome to Managing Widgets application, wher you have CRUD actions to manage your 
                    widgets like Batman!
                </Text>
                <Divider />
                <Text mt={"4"}>
                    Go to my GitHub profile here{" "}
                    <Link color="teal.500" href="https://github.com/mrp4sten" isExternal>
                        Click me!
                    </Link>
                </Text>
            </Box>
        </Flex>
    );
};

export default Welcome;
