#version 330

layout (location = 0) out vec4 color;

in vec2 v_tex;

uniform sampler2D atlas;

void main()
{
	color = texture2D(atlas, v_tex);
}
